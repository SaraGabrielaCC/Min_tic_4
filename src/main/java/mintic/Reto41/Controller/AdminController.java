package mintic.Reto41.Controller;

import mintic.Reto41.Entities.Admin;
import mintic.Reto41.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Admin")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class AdminController {
    private AdminService adminService;
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/all")
    public List<Admin> getAdmin(){
        return adminService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{id}")
    public Optional<Admin> getAdmin(@PathVariable("id") int Id) {
        return adminService.getAdmin(Id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public Admin save(@RequestBody Admin admin) {
        return adminService.save(admin);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public Admin update(@RequestBody Admin admin){
        return adminService.update(admin);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id){
        return adminService.deleteAdmin(id);
    }
}
