package com.harvices.project.expense_tracker_api.model;
import com.harvices.project.expense_tracker_api.model.enums.ExpenseCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private double amount;

    @NotBlank
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ExpenseCategory category;

    @NotNull
    private LocalDate date;

    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}
