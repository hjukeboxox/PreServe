package com.example.PreServe.common;

import com.example.PreServe.domain.entity.Auth;
import com.example.PreServe.domain.entity.AuthEntity;
import com.example.PreServe.domain.repository.AuthRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

	private final PasswordEncoder passwordEncoder;
	private final AuthRepository authRepository;


	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		return this.authRepository.findByUserId(userId)
						.orElseThrow(() -> new UsernameNotFoundException("couldn't find userId -> " + userId));
	}
	public AuthEntity register(Auth.SignUp user) {
		boolean exists = this.authRepository.existsByUserId(user.getUserId());
		if (exists) {
			//throw new AlreadyExistUserException();
			throw new RuntimeException();
		}


		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		var result = this.authRepository.save(user.toEntity());
		return result;
	}

	public AuthEntity authenticate(Auth.SignIn user) {
		var userAuth = this.authRepository.findByUserId(user.getUserId())
						.orElseThrow(() -> new RuntimeException("존재하지 않는 ID입니다."));

		if (!this.passwordEncoder.matches(user.getPassword(), userAuth.getPassword())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}

		return userAuth;
	}

}
