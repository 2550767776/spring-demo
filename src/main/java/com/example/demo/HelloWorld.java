@RestController
public class HelloWorld {

    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name) {
        if (null == name) {
            name = "boy";
        }
        return "hello world" + name;
    }

}
