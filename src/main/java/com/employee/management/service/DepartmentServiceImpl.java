package com.employee.management.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.employee.management.error.DepartmentErrorCodes;
import com.employee.management.exception.DepartmentException;
import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;

@Service
@Profile("!mock")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    DepartmentServiceImpl(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // fetching all department
    @Override
    public List<Department> getAllDepartments() {
        List<Department> depts = (List<Department>) departmentRepository.findAll();
        return depts;
    }

    // fetching department by id
    @Override
    public Department getDepartment(int id) {
        return departmentRepository.findById(id).get();
    }

    // inserting department
    @Override
    public void addDepartment(Department d) {
        departmentRepository.save(d);
    }

    // updating department by id
    @Override
    public void updateDepartment(Department d, int id) {
        if (id == d.getDepartment_ID()) {
            departmentRepository.save(d);
        }
    }

    // deleting all departments
    @Override
    public void deleteAllDepartment() {
        departmentRepository.deleteAll();
    }

    // deleting department by id
    @Override
    public void deleteDepartmentByID(int id) {
        departmentRepository.deleteById(id);
    }

    // patching/updating department by id
    @Override
    public void patchDepartment(Department d, int id) {
        if (id == d.getDepartment_ID()) {
            departmentRepository.save(d);
        }
    }

    @Override
    public List<Department> getHarcodedEmployeeList() {
        List<Department> list = new ArrayList<>();
        list.add(new Department(10, "XYZ Departemnt", "XYZ"));
        return list;
    }

    @Override
    public void printDetails(String dataToPrint) {
        byte[] content = dataToPrint.getBytes();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        try (InputStream dataInputStream = new ByteArrayInputStream(content);) {
            PrintService printService = findPrintService();
            Doc doc = new SimpleDoc(dataInputStream, flavor, null);
            DocPrintJob pj = printService.createPrintJob();
            pj.print(doc, null);
        } catch (PrintException | IOException exception) {
            throw new DepartmentException(DepartmentErrorCodes.DEPARTMENT_PRINTING_EXCEPTION);
        }
    }

    private PrintService findPrintService() {
        return PrintServiceLookup.lookupDefaultPrintService();
    }
}
