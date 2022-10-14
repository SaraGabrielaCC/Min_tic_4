package mintic.Reto41.Repository;

import mintic.Reto41.Entities.Machine;
import mintic.Reto41.Repository.CrudRepository.MachineCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MachineRepository {
    @Autowired
    private MachineCrudRepository machineCrudRepository;
    public List<Machine> getAll(){
        return (List<Machine>) machineCrudRepository.findAll();
    }
    public Optional<Machine> getMachine(int id){
        return machineCrudRepository.findById(id);
    }
    public Machine save(Machine machine){
        return machineCrudRepository.save(machine);
    }

    public void delete(Machine machine){
        machineCrudRepository.delete(machine);
    }
}
