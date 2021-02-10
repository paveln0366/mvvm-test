package com.example.mvvm_test.screens.employees;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_test.R;
import com.example.mvvm_test.adapters.EmployeeAdapter;
import com.example.mvvm_test.pojo.Employee;
import com.example.mvvm_test.pojo.Specialty;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);

        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());

        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
                if (employees != null) {
                    for (Employee employee : employees) {
                        List<Specialty> specialties = employee.getSpecialty();
                        for (Specialty specialty : specialties) {
                            Log.i("Speciality", specialty.getName());
                        }
                    }
                }
            }
        });

        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(EmployeeListActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });

        viewModel.loadData();
    }
}