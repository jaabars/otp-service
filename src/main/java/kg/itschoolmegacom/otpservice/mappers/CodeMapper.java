package kg.itschoolmegacom.otpservice.mappers;

import kg.itschoolmegacom.otpservice.models.dtos.CodeDto;
import kg.itschoolmegacom.otpservice.models.entities.Code;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeMapper {

  CodeMapper INSTANCE = Mappers.getMapper(CodeMapper.class);

  Code codeDtoToCode(CodeDto codeDto);
  CodeDto codeToCodeDto(Code code);
}
