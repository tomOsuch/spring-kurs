package pl.springkurs.shop.payments.adapters.web;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.springkurs.shop.payments.ports.PaymentService;

import javax.validation.Valid;

@RequestMapping("payments/process")
@Controller
@RequiredArgsConstructor
public class PaymentWebController {

    private final PaymentService paymentService;
    private final WebPaymentMapper paymentMapper;

    @GetMapping
    public String showPaymentForm(Model model) {
        var paymentRequestViewModel = new PaymentRequestViewModel();
        paymentRequestViewModel.setValue("100 PLN");
        model.addAttribute("paymentRequest", paymentRequestViewModel);
        return "payments/payment-form";
    }

    @PostMapping
    public String process(@Valid @ModelAttribute("paymentRequest") PaymentRequestViewModel paymentRequestViewModel,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "payments/payment-form";
        }

        var paymentRequest = paymentMapper.toDomain(paymentRequestViewModel);
        var payment = paymentService.process(paymentRequest);
        var paymentViewModel = paymentMapper.toViewModel(payment);
        redirectAttributes.addFlashAttribute("payment", paymentViewModel);
        return "redirect:/payments/payment-summary";
    }

}
