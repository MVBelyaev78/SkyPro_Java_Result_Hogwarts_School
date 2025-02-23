package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    @OneToOne
    private Student student;

    public Avatar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object object) {
        return (this == object ||
                object != null &&
                        getClass() == object.getClass() &&
                        fileSize == ((Avatar) object).getFileSize() &&
                        Objects.equals(id, ((Avatar) object).getId()) &&
                        Objects.equals(filePath, ((Avatar) object).getFilePath()) &&
                        Objects.equals(mediaType, ((Avatar) object).getMediaType()) &&
                        Arrays.equals(data, ((Avatar) object).getData()) &&
                        Objects.equals(student, ((Avatar) object).getStudent()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileSize, id, filePath, mediaType, Arrays.hashCode(data), student);
    }
}
