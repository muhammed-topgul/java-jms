package com.muhammedtopgul.javajms.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author muhammed-topgul
 * @since 25.02.2022 16:34
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyMessage implements Serializable {

    static final long serialVersionUID = 5884851044759029346L;

    private UUID id;
    private String content;
}
