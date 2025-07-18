package ra.ss6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.ss6.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
