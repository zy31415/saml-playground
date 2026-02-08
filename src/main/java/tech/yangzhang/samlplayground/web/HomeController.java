package tech.yangzhang.samlplayground.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        Saml2AuthenticatedPrincipal principal = (Saml2AuthenticatedPrincipal) authentication.getPrincipal();

        Map<String, Object> view = new LinkedHashMap<>();
        view.put("name", principal.getName());
        view.put("relyingPartyRegistrationId", principal.getRelyingPartyRegistrationId());
        view.put("attributes", principal.getAttributes());

        model.addAttribute("info", view);
        return "dashboard";
    }
}