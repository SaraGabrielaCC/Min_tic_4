package mintic.Reto41.Service;

import mintic.Reto41.Entities.Admin;
import mintic.Reto41.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }
    public Optional<Admin> getAdmin(int adminId) {
        return adminRepository.getAdmin(adminId);
    }

    public Admin save(Admin admin){
        if(admin.getIdAdmin()==null){
            return adminRepository.save(admin);
        }else{
            Optional<Admin> e= adminRepository.getAdmin(admin.getIdAdmin());
            if(e.isPresent()){
                return adminRepository.save(admin);
            }else{
                return admin;
            }
        }
    }
    public Admin update(Admin admin){
        if(admin.getIdAdmin()!=null){
            Optional<Admin> e= adminRepository.getAdmin(admin.getIdAdmin());
            if(!e.isPresent()){
                if(admin.getName()!=null){
                    e.get().setName(admin.getName());
                }
                if(admin.getPassword()!=null){
                    e.get().setPassword(admin.getPassword());
                }
                adminRepository.save(e.get());
                return e.get();
            }else{
                return admin;
            }
        }else{
            return admin;
        }
    }
    public boolean deleteAdmin (int id){
        Boolean d = getAdmin(id).map(admin -> {
            adminRepository.delete(admin);
            return true;
        }).orElse(false);
        return d;
    }
}
