package mintic.Reto41.Controller;

import mintic.Reto41.Entities.Machine;
import mintic.Reto41.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Machine")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MachineController {
    @Autowired
    private MachineService machineService;
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/all")
    public List<Machine> getMachines(){
        return machineService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{id}")
    public Optional<Machine> getMachine(@PathVariable("id") int machineId) {
        return machineService.getMachine(machineId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public Machine save(@RequestBody Machine machine) {
        return machineService.save(machine);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public Machine update(@RequestBody Machine machine) {
        return machineService.update(machine);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id){
        return machineService.deleteMachine(id);
    }
}
