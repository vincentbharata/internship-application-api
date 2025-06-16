## 🧩 Soal 4 – Internship Application System

### 📁 Repository: `internship-application-api`

### 🎯 Product Requirement:

Sistem mahasiswa melamar magang ke perusahaan.

### 📡 Endpoint (Minimal 8):

| Method | Endpoint                      | Description             |
| ------ | ----------------------------- | ----------------------- |
| POST   | `/companies`                  | Tambah perusahaan       |
| POST   | `/students`                   | Tambah mahasiswa        |
| POST   | `/applications`               | Ajukan lamaran          |
| GET    | `/applications`               | List semua lamaran      |
| GET    | `/applications/{id}`          | Detail lamaran          |
| PUT    | `/applications/{id}`          | Update status lamaran   |
| GET    | `/students/{id}/applications` | Lamaran milik mahasiswa |
| DELETE | `/applications/{id}`          | Hapus lamaran           |

### 🧪 Validasi:

- `@Email`, `@NotBlank` → student
- `@Pattern` → resume_link
- Enum: `status` (PENDING, ACCEPTED, REJECTED)

---
