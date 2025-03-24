//package com.example.ejerciciospringbatch.proxies;
//
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import com.example.ejerciciospringbatch.models.*;
//
//@FeignClient(name="photos", url="https://picsum.photos")
//public interface PhotoProxy {
//    @GetMapping(value = "/v2/list?limit=1000")
//    List<PhotoDTO> getAll();
//    @GetMapping("/id/{id}/info")
//    PhotoDTO getOne(@PathVariable int id);
//    
//    @PostMapping(path = "/photos", consumes = { "application/json"} )
//    void send(@RequestBody PhotoDTO item);
//}