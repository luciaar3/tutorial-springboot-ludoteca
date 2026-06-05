package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "")
    public Page<LoanDto> findPage(@RequestBody LoanSearchDto dto) {
        Page<Loan> page = this.loanService.findPage(dto);

        List<LoanDto> dtoList = page.getContent().stream().map(e -> modelMapper.map(e, LoanDto.class)).collect(Collectors.toList());

        return new PageImpl<>(dtoList, dto.getPageable(), page.getTotalElements());

    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.loanService.delete(id);
    }
}