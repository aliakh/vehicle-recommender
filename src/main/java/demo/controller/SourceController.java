package demo.controller;

import demo.domain.VehicleFilter;
import demo.service.LoadingService;
import demo.service.PreparingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

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

    @RequestMapping(path = "/source/load/sse", method = RequestMethod.GET)
    public SseEmitter getEvents() {
        return loadingService.getEvents();
    }

    @RequestMapping(value = "/source/prepare", method = RequestMethod.GET)
    @ResponseBody
    public Object process(@RequestParam(value = "makes", required = false) String makes,
                          @RequestParam(value = "years", required = false) String years) {
        return preparingService.process(new VehicleFilter(makes, years));
    }
}
