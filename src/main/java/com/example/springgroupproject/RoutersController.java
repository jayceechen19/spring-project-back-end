package com.example.springgroupproject;

import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/routers")
public class RoutersController {
    private final RoutersRepository routerRepo;

    public RoutersController(RoutersRepository routerRepo) {
        this.routerRepo = routerRepo;
    }
    @GetMapping("/all")
    public Iterable<Router> allRouters() {
        return this.routerRepo.findAll();
    }
    @GetMapping("/{id}")
    public Router allRouters(@PathVariable("id") Long id) {
        return this.routerRepo.findById(id).get();
    }
    @PostMapping("/new")
    public Router create(@RequestBody Router router) {
        return this.routerRepo.save(router);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        this.routerRepo.deleteById(id);
    }
    @GetMapping("/active")
    public Iterable<Router> allActiveRouters(){
        return this.routerRepo.findByActive(true);
    }
    @PatchMapping("/inactivate")
    public Router inactivate(@RequestParam Long id, @RequestParam Date date){
        Router router = this.routerRepo.findById(id).get();
        router.setActive(false);
        return this.routerRepo.save(router);
    }
    //Only allows ip address, mac address, and name change
    @PatchMapping("/patch/{id}")
    public Router patch(@PathVariable("id") Long id, @RequestBody Router routerPatch){
        Router router = this.routerRepo.findById(id).get();
        if(routerPatch.getIpAddress() != null){
            router.setIpAddress(routerPatch.getIpAddress());
        }
        if(routerPatch.getMacAddress() != null){
            router.setMacAddress(routerPatch.getMacAddress());
        }
        if(routerPatch.getName() != null){
            router.setName(routerPatch.getName());
        }
        return this.routerRepo.save(router);
    }

}
