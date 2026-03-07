CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    group_id VARCHAR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE user_roles (
    user_id BIGINT REFERENCES users(id),
    role_id BIGINT REFERENCES roles(id)
);

CREATE TABLE groups (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    faculty_name VARCHAR,
    course_number INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR NOT NULL,
    group_id BIGINT REFERENCES groups(id),
    student_number VARCHAR UNIQUE,
    email VARCHAR,
    phone VARCHAR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lessons (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    teacher_name VARCHAR,
    classroom VARCHAR,
    lesson_number INTEGER ,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    group_id BIGINT REFERENCES groups(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE schedules (
    id BIGSERIAL PRIMARY KEY,
    group_id BIGINT REFERENCES groups(id) ON DELETE CASCADE,
    schedule_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE schedule_lessons (
    id BIGSERIAL PRIMARY KEY,
    schedule_id BIGINT REFERENCES schedules(id) ON DELETE CASCADE,
    lesson_id BIGINT REFERENCES lessons(id) ON DELETE CASCADE,
    lesson_order INTEGER NOT NULL
);

CREATE TABLE attendance (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT REFERENCES students(id) ON DELETE CASCADE,
    schedule_lesson_id BIGINT REFERENCES schedule_lessons(id) ON DELETE CASCADE,
    status VARCHAR,
    attendance_date DATE NOT NULL,
    marked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
