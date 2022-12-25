package Employee;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private EmployeeInterface repository;
	public EmployeeController(EmployeeInterface repository) {
		this.repository = repository;
	}
	// Getting all the records
	@GetMapping("/employee")
	List<Employee> get(){
		return repository.findAll();
	}
	
	// Inserting the record 
	@PostMapping("/employee")
	Employee save(@RequestBody Employee newEmployee){
		return repository.save(newEmployee);
	}
	
	// Getting Particular record based on id
	@GetMapping("/employee/{id}")
	Optional<Employee> getid(@PathVariable Integer id) {
		return repository.findById(id);
	}
	
	// Updating a record based on id
	@PutMapping("/employee/{id}")
	Optional<Employee> update(@PathVariable Integer id, @RequestBody Employee newEmployee) {
		return repository.findById(id).map(employee->{
			employee.setPass(newEmployee.getPass());
			employee.setPhone(newEmployee.getPhone());
			Employee updatedemployee = repository.save(employee);
			return updatedemployee;
		});
		
	}
	
	// Deleting the record based on id
	@DeleteMapping("/employee/{id}")
	void delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}