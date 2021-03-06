package pro.ivashchuk.employerresearcher.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CoverLetter implements Comparable<CoverLetter> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private String name;
    private String date;
    private String salutation;
    private String[] paragraphs;
    private String closing;
    private String signature;

    public CoverLetter(String name, String date, String salutation, String[] paragraphs, String closing, String signature) {
        this.name = name;
        this.date = date;
        this.salutation = salutation;
        this.paragraphs = paragraphs;
        this.closing = closing;
        this.signature = signature;
    }

    @Override
    public int compareTo(CoverLetter coverLetter) {
        return (this.getName().compareTo(coverLetter.getName()));
    }
}