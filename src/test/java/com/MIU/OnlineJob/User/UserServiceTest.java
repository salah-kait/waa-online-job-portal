package com.MIU.OnlineJob.User;


import com.MIU.OnlineJob.DataAccess.RoleRepository;
import com.MIU.OnlineJob.DataAccess.UserRepository;
import com.MIU.OnlineJob.Models.Role;
import com.MIU.OnlineJob.Models.User;
import com.MIU.OnlineJob.Models.enums.RoleName;
import com.MIU.OnlineJob.Services.RoleService;
import com.MIU.OnlineJob.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;;
import org.mockito.BDDMockito;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Mock
    RoleService roleService;

    @Test
    public void testCreateUser(){
        User user = new User("test", "testusername",
                "test@test.com", "PASSWORD");

        Role userRole = roleService.findRoleByName(RoleName.ROLE_ADMIN);
        user.setRoles(Collections.singleton(userRole));

        BDDMockito.given(userRepository.findByEmail(user.getEmail())).willReturn(null);
        BDDMockito.given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.save(user);

        assertThat(savedUser).isNotNull();
        verify(userRepository).save(any(User.class));

    }
}
