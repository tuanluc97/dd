package app.tuanluc.service

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

/**
 * @author tuanluc on 21/09/2019
 */
@Component
@CompileStatic
class Service {

    String handle(String data, String replace, String input) {
        return data.replace(replace, input)
    }
}
