package demo.controller;

import demo.domain.VehicleFilter;
import demo.service.LoadingService;
import demo.service.PreparingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SourceController {

    @Autowired
    private LoadingService loadingService;

    @Autowired
    private PreparingService preparingService;

    @RequestMapping(value = "/source/load", method = RequestMethod.GET)
    @ResponseBody
    public String load(@RequestParam(value = "makes", required = false) String makes,
                       @RequestParam(value = "years", required = false) String years) {
        return loadingService.load(new VehicleFilter(makes, years));
    }

    @RequestMapping(value = "/source/prepare", method = RequestMethod.GET)
    @ResponseBody
    public Object process(@RequestParam(value = "makes", required = false) String makes,
                          @RequestParam(value = "years", required = false) String years) {
        return preparingService.process(new VehicleFilter(makes, years));
    }
}
