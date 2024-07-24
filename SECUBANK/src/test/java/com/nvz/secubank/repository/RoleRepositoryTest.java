package com.nvz.secubank.repository;

import com.nvz.secubank.entity.Role;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @ParameterizedTest
    @ValueSource(strings = {"USER", "ADMIN"})
    void testFindRoleByName(String name) {
        Role role = new Role();
        role.setName(name);

        roleRepository.save(role);

        Role foundRole = roleRepository.findRoleByName(name);
        assertNotNull(foundRole);
        assertEquals(role.getName(), foundRole.getName());
    }
}