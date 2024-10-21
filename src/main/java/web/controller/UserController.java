package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/addUser")
    public String showAddUserPage() {
        return "addUser";
    }

    @GetMapping("/deleteUser")
    public String showDeleteUserPage() {
        return "deleteUser";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("age") int age, Model model) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id, Model model) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/updateUser")
    public String updateUser() {
        return "/updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("id") int id, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surname, @RequestParam(value = "age", required = false) Integer age) {
        User user = userService.getUser(id);
        if (user != null) {
            if (name != null && !name.isEmpty()) {
                user.setName(name);
            }
            if (surname != null && !surname.isEmpty()) {
                user.setSurname(surname);
            }
            if (age != null) {
                user.setAge(age);
            }
            userService.updateUser(user);
        }
        return "redirect:/";
    }

}