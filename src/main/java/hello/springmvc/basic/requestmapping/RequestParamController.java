package hello.springmvc.basic.requestmapping;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
        @RequestParam String memberName,
        @RequestParam int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
        String membername,
        int age
    ) {
        log.info("username={}, age={}", membername, age);
        return "ok";
    }

    /**
     * @RequestParam.required /request-param-required -> username이 없으므로 예외 * 주의!
     * /request-param-required?username= -> 빈문자로 통과 * 주의! /request-param-required int age -> null을
     * int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        @RequestParam(required = true) String username,
        @RequestParam(required = false) Integer age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam - defaultValue 사용 * 참고: defaultValue는 빈 문자의 경우에도 적용 *
     * /request-param-default?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
        @RequestParam(defaultValue = "guest") String username,
        @RequestParam(defaultValue = "-1") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     * 파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자.
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
        @RequestParam Map<String, Object> paramMap
    ) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }




}
