package com.example.healthcare.hessian.config;

import com.example.healthcare.hessian.service.DoctorsService;
import com.example.healthcare.hessian.service.DoctorsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class HessianRMIConfig {

    @Autowired
    private DoctorsService doctorsService;

    @Bean(name = "/interconnect/doctors")
    public RemoteExporter remoteExporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(doctorsService);
        exporter.setServiceInterface(DoctorsService.class);
        return exporter;
    }
}
