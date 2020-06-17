package com.uca.capas.controller;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private EstudianteService estudianteService;

    @RequestMapping("/inicio")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        Estudiante estudiante = new Estudiante();
        mav.addObject("estudiante",estudiante);
        mav.setViewName("index");
        return mav;
    }
    
    @RequestMapping("/insertarEstudiante")
    public ModelAndView insert(@Valid @ModelAttribute Estudiante estudiante, BindingResult result){
        ModelAndView mav = new ModelAndView();
        
        if(result.hasErrors()) {
        	mav.setViewName("index");
        }else {
        	 try {
        		 estudianteService.save(estudiante);
                 Estudiante student = new Estudiante();
                 mav.addObject("estudiante", student);
                 mav.setViewName("index");
             }catch (Exception e){
                 e.printStackTrace();
             }
        }
        return mav;
    }
    
    @RequestMapping(value = "/borrarEstudiante", method=RequestMethod.POST)
    public ModelAndView delete(@RequestParam(value="codigo") int id){
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
    	 try {
    		 estudianteService.delete(id);
    		 estudiantes = estudianteService.findAll();
    		 mav.addObject("estudiantes", estudiantes);
    	     mav.setViewName("listado");
         }catch (Exception e){
             e.printStackTrace();
         }
        return mav;
    }
    
    @RequestMapping("/listado")
    public ModelAndView lista(){
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
        try {
            estudiantes = estudianteService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("listado");
        return mav;
    }

    @RequestMapping(value = "/editarEstudiante", method = RequestMethod.POST)
    public ModelAndView editar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
        if(result.hasErrors()) {
            mav.setViewName("editar");
        }else {
            try {
                estudianteService.save(estudiante);
                estudiantes = estudianteService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("listado");
        return mav;
    }

    @PostMapping("/edit")
    public ModelAndView editview(@RequestParam(value="codigo") int id) {
        ModelAndView mav = new ModelAndView();
        Estudiante estudiante = estudianteService.findOne(id);
        mav.addObject("estudiante",estudiante);
        mav.setViewName("editar");
        return mav;
    }
}
