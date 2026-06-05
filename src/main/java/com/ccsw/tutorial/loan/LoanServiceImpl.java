package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {

        Specification<Loan> spec = (root, query, builder) -> null;

        if (dto.getIdGame() != null) {
            spec = spec.and(new LoanSpecification(new SearchCriteria("game", ":", dto.getIdGame())));
        }

        if (dto.getIdClient() != null) {
            spec = spec.and(new LoanSpecification(new SearchCriteria("client", ":", dto.getIdClient())));
        }

        if (dto.getDate() != null) {
            spec = spec.and(new LoanSpecification(new SearchCriteria("date", "isBetween", dto.getDate())));
        }

        return this.loanRepository.findAll(spec, dto.getPageable());
    }

    @Override
    public void save(Long id, LoanDto dto) throws Exception {

    }

    @Override
    public void delete(Long id) {
        this.loanRepository.deleteById(id);
    }

}
