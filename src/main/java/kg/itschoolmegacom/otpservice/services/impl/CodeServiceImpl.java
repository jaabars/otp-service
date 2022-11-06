package kg.itschoolmegacom.otpservice.services.impl;

import kg.itschoolmegacom.otpservice.mappers.CodeMapper;
import kg.itschoolmegacom.otpservice.models.dtos.CodeDto;
import kg.itschoolmegacom.otpservice.models.entities.Code;
import kg.itschoolmegacom.otpservice.models.entities.User;
import kg.itschoolmegacom.otpservice.models.enums.Status;
import kg.itschoolmegacom.otpservice.repositrories.CodeRepo;
import kg.itschoolmegacom.otpservice.services.CodeService;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {

  private final CodeRepo codeRepo;

  public CodeServiceImpl(CodeRepo codeRepo) {
    this.codeRepo = codeRepo;
  }

  @Override
  public CodeDto findUserCode(User user) {
    Code code = codeRepo.findByUserAndStatusIs(user, Status.NEW);

    return CodeMapper.INSTANCE.codeToCodeDto(code);
  }

  @Override
  public CodeDto update(CodeDto codeDto) {
    Code code = CodeMapper.INSTANCE.codeDtoToCode(codeDto);
    code = codeRepo.save(code);

    return CodeMapper.INSTANCE.codeToCodeDto(code);
  }

  @Override
  public CodeDto save(CodeDto newCode) {
    Code code = CodeMapper.INSTANCE.codeDtoToCode(newCode);
    code = codeRepo.save(code);

    return CodeMapper.INSTANCE.codeToCodeDto(code);
  }
}
