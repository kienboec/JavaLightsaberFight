package bif3.swe1.tdd.weapon;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Lightsaber {
    @Getter
    private LightsaberColor color;

    public Lightsaber() {
        color = LightsaberColor.NONE;
    }

    public Lightsaber setBelongTo(String fighter) {
        color = switch( fighter ) {
            case "Luke" -> LightsaberColor.GREEN;
            case "Darth" -> LightsaberColor.RED;
            case "Yoda" -> LightsaberColor.BLUE;
            default -> LightsaberColor.NONE;
        };

        /*
        // or old school
        switch(fighter){
            case "Luke":
                color = LightsaberColor.GREEN;
                break;
            case "Darth":
                color = LightsaberColor.RED;
                break;
            case "Yoda":
                color = LightsaberColor.BLUE;
                break;
            default:
                color = LightsaberColor.NONE;
                break;
        }*/

        return this;
    }

}
