package com.example.springgroupproject;

import org.springframework.web.bind.annotation.*;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/switches")
public class SwitchController {
    private final SwitchRepository switchRepo;
//    private final RoutersRepository routerRepo;

//    public SwitchController(SwitchRepository switchRepo, RoutersRepository routerRepo) {
//        this.switchRepo = switchRepo;
//        this.routerRepo = routerRepo;
//    }
    public SwitchController(SwitchRepository switchRepo) {
        this.switchRepo = switchRepo;
    }
    @GetMapping("/all")
    public Iterable<Switch> allSwitches(){
        return this.switchRepo.findAll();
    }

    @PostMapping("/new")
    public Switch create(@RequestBody Switch newSwitch){
        return this.switchRepo.save(newSwitch);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        this.switchRepo.deleteById(id);
    }

    @GetMapping("/active")
    public Iterable<Switch> allActiveSwitches(){
        return this.switchRepo.findByActive(true);
    }
    @PatchMapping("/inactivate")
    public Switch inactivate(@RequestParam Long id, @RequestParam Date date){
        Switch swtch = this.switchRepo.findById(id).get();
        swtch.setActive(false);
        return this.switchRepo.save(swtch);
    }
    @PatchMapping("/reassign/{switchid}")
    public Switch patch(@PathVariable("switchid") Long switchid, @RequestBody Router router){
        Switch swtch = this.switchRepo.findById(switchid).get();
        swtch.setRouter(router);

        return this.switchRepo.save(swtch);

    }
    @PatchMapping("/patch/{id}")
    public Switch patch(@PathVariable("id") Long id, @RequestBody Switch switchPatch){
        Switch swtch = this.switchRepo.findById(id).get();
        if(switchPatch.getIpAddress() != null){
            swtch.setIpAddress(switchPatch.getIpAddress());
        }
        if(switchPatch.getMacAddress() != null){
            swtch.setMacAddress(switchPatch.getMacAddress());
        }
        if(switchPatch.getName() != null){
            swtch.setName(switchPatch.getName());
        }
        if(switchPatch.getActive() != null){
            swtch.setActive(switchPatch.getActive());
        }
        if(switchPatch.getStatusColor() != null){
            swtch.setStatusColor(switchPatch.getStatusColor());
        }
        if(switchPatch.getLastActive() !=null){
            swtch.setLastActive(switchPatch.getLastActive());
        }
        return this.switchRepo.save(swtch);
    }


}
