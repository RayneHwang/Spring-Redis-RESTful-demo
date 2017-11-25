package pku.netlab.springdataredisdemo.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    @Getter String id;
    @Getter String name;
    @Getter String password;
}
