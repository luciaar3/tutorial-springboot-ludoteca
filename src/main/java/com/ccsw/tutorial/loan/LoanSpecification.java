package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.loan.model.Loan;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class LoanSpecification implements Specification<Loan> {

    private SearchCriteria criteria;

    public LoanSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Loan> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (this.criteria.getOperation().equalsIgnoreCase(":")) {
            if (this.criteria.getKey().equals("game") && this.criteria.getValue() != null) {
                return builder.equal(root.get("game").get("id"), this.criteria.getValue());
            }
            if (this.criteria.getKey().equals("client") && this.criteria.getValue() != null) {
                return builder.equal(root.get("client").get("id"), this.criteria.getValue());
            }
        }

        if (this.criteria.getOperation().equalsIgnoreCase("isBetween") && this.criteria.getValue() != null) {
            java.util.Date searchDate = (java.util.Date) this.criteria.getValue();

            Predicate startDateLessThanOrEqual = builder.lessThanOrEqualTo(root.get("loanDate"), searchDate);
            Predicate endDateGreaterThanOrEqual = builder.greaterThanOrEqualTo(root.get("returnDate"), searchDate);

            return builder.and(startDateLessThanOrEqual, endDateGreaterThanOrEqual);
        }
        return null;
    }
}
