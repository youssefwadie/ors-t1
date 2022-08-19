package com.github.youssefwadie.ors.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CancellationCommand {
    private Long pnr;
    private LocalDate requestDate;

    public CancellationCommand() {
        this.requestDate = LocalDate.now();
    }
}
