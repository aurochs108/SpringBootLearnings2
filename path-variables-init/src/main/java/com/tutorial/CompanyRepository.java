package com.tutorial;

import java.util.List;

interface CompanyRepository {
    List<Company> findAll();
    Company findOne(String name);
}
