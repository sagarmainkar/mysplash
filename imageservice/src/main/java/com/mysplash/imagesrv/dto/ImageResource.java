package com.mysplash.imagesrv.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ImageDetails")
public class ImageResource {

    @Id
    @SequenceGenerator(
            name = "image_id_sequence",
            sequenceName = "image_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_id_sequence"
    )
    @Column(name = "ImageId")
    private Integer Id;
    @Column(name = "UserId")
    private Integer userId;
    @Column(name = "ImageName")
    private String imageName;
    @Column(name = "ImageURL")
    private String imageUrl;
}
