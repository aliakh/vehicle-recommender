package demo.controller;

import com.codahale.metrics.annotation.Timed;
import demo.domain.VehicleFilter;
import demo.service.LoadingService;
import demo.service.PreparingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SourceController {

    @Autowired
    private LoadingService loadingService;

    @Autowired
    private PreparingService preparingService;

    @RequestMapping(value = "/source/load", method = RequestMethod.GET)
    @ResponseBody
    @Timed
    public boolean load(@RequestParam(value = "makes", required = false) String makes,
                       @RequestParam(value = "years", required = false) String years) {
        return loadingService.load(new VehicleFilter(makes, years));
    }

    @RequestMapping(path = "/source/load/progress", method = RequestMethod.GET)
    @Timed
    public SseEmitter getProgressEmitter() {
        return loadingService.getProgressEmitter();
    }

    @RequestMapping(path = "/source/load/errors", method = RequestMethod.GET)
    @Timed
    public SseEmitter getErrorEmitter() {
        return loadingService.getErrorEmitter();
    }

    @RequestMapping(value = "/source/prepare", method = RequestMethod.GET)
    @ResponseBody
    @Timed
    public Object process(@RequestParam(value = "makes", required = false) String makes,
                          @RequestParam(value = "years", required = false) String years) {
        return preparingService.process(new VehicleFilter(makes, years));
    }
}
