package com.example.thiaco.service.employee;

import com.example.thiaco.model.employee.HistoryEmployee;
import com.example.thiaco.repository.HistoryEmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoryEmployeeService implements IHistoryEmployeeService {

    @Autowired
    private HistoryEmployeeRepository historyEmployeeRepository;
    @Override
    public List<HistoryEmployee> findAll() {
        return historyEmployeeRepository.findAll();
    }

    @Override
    public Optional<HistoryEmployee> findById(Long id) {
        return historyEmployeeRepository.findById(id);
    }

    @Override
    public HistoryEmployee save(HistoryEmployee historyEmployee) {
        return historyEmployeeRepository.save(historyEmployee);
    }

    @Override
    public void delete(HistoryEmployee historyEmployee) {

    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public List<HistoryEmployee> findHistoryEmployeesByEmployee_id(Long manv) {
        return historyEmployeeRepository.findHistoryEmployeesByEmployeeId(manv);
    }
}
