package com.miniproject.onlinequizapplication.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse <T>{

    private String status;

    private T result;
}
