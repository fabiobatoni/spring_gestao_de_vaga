package br.com.fabiobatoni.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabiobatoni.gestao_vagas.exceptions.UserFoundException;
import br.com.fabiobatoni.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.fabiobatoni.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  private void execute(CompanyEntity companyEntity) {
    
    this.companyRepository
    .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
    .ifPresent((user) -> {
      throw new UserFoundException();
    });

    this.companyRepository.save(companyEntity);
  }
}
