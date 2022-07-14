import { Gender } from "../enum/gender.enum";

export interface Patient {
    id: number;
    lastName: string;
    firstName: string;
    birthdate: Date;
    sex: Gender
    address: string;
    phone: string;
}