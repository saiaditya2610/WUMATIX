package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.demo.Component.CustomOAuth2User;
import com.example.demo.Component.MyUserDetails;

import com.example.demo.beans.Course;
import com.example.demo.beans.User;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.authentication.AuthenticationManager;


@Controller
public class WebController {
    

    private static final Logger log=LoggerFactory.getLogger(WebController.class);
    private RestApiController main;
    private UserService userserv;
  

    @Autowired
    public WebController(RestApiController main,UserService userserv) {
        this.main = main;
        this.userserv=userserv;
    
    }


    @RequestMapping(value = "/")
    public String defaultURL()
    {
        log.info("Redirecting to the Home Page...................");
        return "redirect:/Main";

    }
    @RequestMapping(value = "/Main")
    public String Home(Model model,@AuthenticationPrincipal MyUserDetails user,@AuthenticationPrincipal CustomOAuth2User googleuser)
    {
        List<Course> course=main.getAll().getBody();
        model.addAttribute("Course", course);
        //model.addAttribute("user", user.getUsername());
        if(googleuser==null)
        {
            model.addAttribute("user",user.getUsername());

        }
        if(user==null){
            model.addAttribute("user", googleuser.getName());
        }
        

        return "home";

    }

    @RequestMapping(value="/del/{id}")
    public String deleteItem(@PathVariable Long id)
    {
        log.warn("About to Delete details...............................");
        System.out.println("Inside this");
        main.delCourse(id);
        log.info("deleted ................................................");
        return "redirect:/Main";
    }

    @RequestMapping(value="/addCourse",method = RequestMethod.GET)
    public String saveItem(Model model,@AuthenticationPrincipal MyUserDetails user,@AuthenticationPrincipal CustomOAuth2User googleuser)
    {
        System.out.println("In add");
        
        model.addAttribute("COURSE", new Course());

        //model.addAttribute("user", user.getUsername());
        if(googleuser==null)
        {
            model.addAttribute("user",user.getUsername());

        }
        if(user==null){
            model.addAttribute("user", googleuser.getName());
        }
        
        return "add";
        
    }


    @RequestMapping(value="/saveItem",method = RequestMethod.POST)
    public String finalSave(@Valid @ModelAttribute("COURSE") Course COURSE,BindingResult res,Model model )
    {
        if(res.hasErrors())
        {
            log.error("Validation Failed");
            return "add";
        }
        log.info("Created");
        main.create(COURSE);

        return "redirect:/Main";
    }


    @RequestMapping(value="/edit/{id}")
    public String editData(@PathVariable("id") long id,Model model)
    {

        Optional<Course> course=main.getOne(id).getBody();
        model.addAttribute("COURSE",course);
       
        return "add";
    }

    @RequestMapping(value = "/userDetails")
    public String addUser(Model model,@AuthenticationPrincipal MyUserDetails userdetails,@AuthenticationPrincipal CustomOAuth2User googleuser)
    {
        List<User> user=userserv.getAllUsers().getBody();
        
        if(googleuser==null)
        {
            model.addAttribute("user",userdetails.getUsername());

        }
        if(userdetails==null){
            model.addAttribute("user", googleuser.getName());
        }
        
        //model.addAttribute("user", userdetails.getUsername());
        
        model.addAttribute("USERS", user);

        return "user";

    }


    @RequestMapping(value="/delUser/{id}")
    public String delUser(@PathVariable("id") Long id)
    {

        userserv.deleteUser(id);

        return "redirect:/userDetails";
    
    }

    @RequestMapping(value="/addUserDetails")
    public String addUserDetails(Model model,@AuthenticationPrincipal MyUserDetails user,@AuthenticationPrincipal CustomOAuth2User googleuser)
    {
        
        model.addAttribute("userNew",new User());
        if(googleuser==null)
        {
            model.addAttribute("user",user.getUsername());

        }
        if(user==null){
            model.addAttribute("user", googleuser.getName());
        }
        
        //model.addAttribute("users", user.getUsername());
        return "adduser";
    }

    @RequestMapping(value="/saveUser")
    public String saveUserDetails(@ModelAttribute("userNew") User userNew)
    {
        userNew.setProvider("LOCAL");
        userserv.saveUser(userNew);
        return "redirect:/userDetails";
    }

    

   




    




}
