package dto;

public class DeliveryAgent {
    String agentName;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public DeliveryAgent(String name) {
        agentName = name;
    }

}
