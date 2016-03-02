package demo.controller;

import demo.repository.CommonRepository;
import demo.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TargetController {

    @Autowired
    private CommonRepository vehicleRepository;

    @Autowired
    private MatchingService matchingService;

    @RequestMapping(value = "/target/makes", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getMakes() {
        return vehicleRepository.getMakes();
    }

    @RequestMapping(value = "/target/models", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getModels(@RequestParam(value = "make", required = true) String make) {
        return vehicleRepository.getModels(make);
    }

    @RequestMapping(value = "/target/years", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getYears(@RequestParam(value = "make", required = true) String make,
                                 @RequestParam(value = "model", required = true) String model) {
        return vehicleRepository.getYears(make, model);
    }

    @RequestMapping(value = "/target/styles", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getStyles(@RequestParam(value = "make", required = true) String make,
                               @RequestParam(value = "model", required = true) String model,
                               @RequestParam(value = "year", required = true) String year) {
        return vehicleRepository.getStyles(make, model, year);
    }

    @RequestMapping(value = "/target/match", method = RequestMethod.GET)
    @ResponseBody
    public Object match(@RequestParam(value = "styleId", required = true) String styleId) {
        return matchingService.match(styleId);
    }
}
