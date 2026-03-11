package com.tambola.game.rule;

import com.tambola.exception.UnsupportedGameTypeException;
import com.tambola.game.strategy.BottomLineStrategy;
import com.tambola.game.strategy.FullHouseStrategy;
import com.tambola.game.strategy.MiddleLineStrategy;
import com.tambola.game.strategy.TopLineStrategy;
import com.tambola.model.GameType;

import java.util.EnumMap;
import java.util.Map;

import static com.tambola.model.GameType.*;

public class GameRuleFactory {

    private final Map<GameType, GameRule> ruleRegistry;

    public GameRuleFactory() {
        ruleRegistry = new EnumMap<>(GameType.class);
        ruleRegistry.put(TOP_LINE, new StandardGameRule(new TopLineStrategy()));
        ruleRegistry.put(MIDDLE_LINE, new StandardGameRule(new MiddleLineStrategy()));
        ruleRegistry.put(BOTTOM_LINE, new StandardGameRule(new BottomLineStrategy()));
        ruleRegistry.put(FULL_HOUSE, new StandardGameRule(new FullHouseStrategy()));
        ruleRegistry.put(EARLY_FIVE, new EarlyFiveGameRule());
    }

    public GameRule getRuleFor(GameType gameType) {
        GameRule rule = ruleRegistry.get(gameType);
        if (rule == null) {
            throw new UnsupportedGameTypeException("No rule registered for game type: " + gameType);
        }
        return rule;
    }
}
