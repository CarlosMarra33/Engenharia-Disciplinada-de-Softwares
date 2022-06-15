//package com.venturaHR.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class RespostaVagaCandidato {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "resposta_candidato_id", nullable = false)
//    private Long respostaCandidatoId;
//
//    @OneToOne
//    @JoinColumn(name = "candidato")
//    private RespostaVaga respostaVaga;
//    @OneToOne
//    @JoinColumn(name = "vaga")
//    private Vaga vaga;
//}
