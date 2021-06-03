package propensi.a04.sisdi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
                authorizeRequests()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/lembur/list").hasAnyAuthority("Karyawan","Manajer Pendidikan","Kepala Bagian","Pimpinan Unit","Manajer SDI","Pengurus Harian","Wakil Pengurus Harian")
                .antMatchers("/lembur/tambah/**").hasAnyAuthority("Karyawan","Manajer Pendidikan","Kepala Bagian","Pimpinan Unit","Manajer SDI","Pengurus Harian","Wakil Pengurus Harian")
                .antMatchers("/lembur/detail/**").hasAnyAuthority("Karyawan","Manajer Pendidikan","Kepala Bagian","Pimpinan Unit","Manajer SDI","Pengurus Harian","Wakil Pengurus Harian")
                .antMatchers("/lembur/delete/**").hasAnyAuthority("Karyawan","Manajer Pendidikan","Kepala Bagian","Pimpinan Unit","Manajer SDI","Pengurus Harian","Wakil Pengurus Harian")
                .antMatchers("/lembur/ubah/**").hasAnyAuthority("Karyawan","Manajer Pendidikan","Kepala Bagian","Pimpinan Unit","Manajer SDI","Pengurus Harian","Wakil Pengurus Harian")
                .antMatchers("/lembur/list/verifikasi").hasAnyAuthority("Kepala Bagian","Pimpinan Unit","Manajer SDI")
                .antMatchers("/lembur/detail/verifikasi/**").hasAnyAuthority("Kepala Bagian","Pimpinan Unit","Manajer SDI")
                .antMatchers("/cuti/kelola").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/cuti/detail/kelola").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/cuti/tolak").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/cuti/setujui").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/pengaduan/kelola/view/selesai").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/pengaduan/kelola/view/teruskan").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/pengaduan/kelola/view/tolak").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/pengaduan/kelola/view").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/pengaduan/view-all").hasAnyAuthority("Pimpinan Unit", "Kepala Bagian", "Manajer SDI")
                .antMatchers("/payroll").hasAnyAuthority("Manajer SDI", "Wakil Pengurus Harian")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("adminn").password(encoder().encode("adminn"))
                .roles("admin");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }


}

