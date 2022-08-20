package pl.tomaszosuch.springkurs.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static pl.tomaszosuch.springkurs.shop.commons.aop.Lock.LockType.READ;

@Aspect
@Component
@Log
public class Locker {

    private final Map<String, ReadWriteLock> lockMap = Collections.synchronizedMap(new HashMap<>());

    @Around("@annotation(lock)")
    public Object lock(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        var lockName = lock.value();
        lockMap.putIfAbsent(lockName, new ReentrantReadWriteLock());
        var currentLock = lockMap.get(lockName);
        var targetLock = lock.type().equals(READ) ? currentLock.readLock() : currentLock.writeLock();
        targetLock.lock();
        try {
            return joinPoint.proceed();
        } finally {
            targetLock.unlock();
        }

    }
}
