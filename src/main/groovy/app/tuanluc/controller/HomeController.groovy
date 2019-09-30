package app.tuanluc.controller

import app.tuanluc.service.Service
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
@CompileStatic
class HomeController {

    @Autowired
    Service service

    @RequestMapping(value = ["/", "/home"], method = RequestMethod.GET)
    String home(Model model) {
        return "home"
    }

    @PostMapping("/result")
    String sumOfferByAdvertiser(Model model, @RequestParam String data
                                , @RequestParam String replace
                                , @RequestParam String input) {
        String result = service.handle(data, replace, input)
        model.addAttribute('result', result)
        return "search"
    }
}
