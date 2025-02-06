package com.vaxi.spring_boot_optica.Model.Ojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OjoRefraccion  {
  private int esfera;
  private int cilindro;
  private Integer eje;
  private int add;
  private int dnp;
  private int agudeza_visual_sc;
  private int agudeza_visual_cc;
}
