import { RiskLevel } from "../enum/risk-level.enum";
import { TriggerTerms } from "../enum/trigger-terms.enum";

export interface Assessment {
    id: number;
    patientLastName: string;
    patientFirstName: string;
    age: number;
    riskLevel: RiskLevel;
    terms: TriggerTerms[];
}