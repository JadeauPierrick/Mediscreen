export interface Note {
    id: number;
    patientId: number;
    createdAt: Date;
    updatedAt: Date;
    content: string;
}